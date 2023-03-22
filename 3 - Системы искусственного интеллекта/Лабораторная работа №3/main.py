from math import log
import matplotlib
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.metrics import roc_curve, precision_recall_curve
import warnings
import plotly.express as px

matplotlib.use("TkAgg")
warnings.filterwarnings("ignore")


# freq(Cj,T) - сколько элементов множества T принадлежат классу Cj
def freq(data_part, cj):
    return (data_part.GRADE == cj).sum()


# энтропия для множества data_part
def info(data_part):
    sum = 0
    for class_value in data_part.GRADE.unique():
        p = freq(data_part, class_value) / data_part.shape[0]
        sum += p * log(p, 2)
    sum = sum * (-1)
    return sum


# условная энтропия
def info_x(data_part, partition_field):
    sum = 0
    for field_value in data_part[partition_field].unique():
        subset = data_part[data_part[partition_field] == field_value]
        sum += subset.shape[0] * info(subset) / data_part.shape[0]
    return sum


def split_info_x(data_part, partition_field):
    sum = 0
    for field_value in data_part[partition_field].unique():
        subset = data_part[data_part[partition_field] == field_value]
        p = subset.shape[0] / data_part.shape[0]
        sum += p * log(p, 2)
    sum = sum * (-1)
    return sum


# нормированный прирост информации
def gain_ratio(data_part, partition_field):
    return (info(data_part) - info_x(data_part, partition_field)) / split_info_x(data_part, partition_field)

#построение дерева решений
class C4point5:

    def __init__(self, data_part):
        subset_fields = data_part.drop(columns='GRADE').columns

        subset_fields_gain_ratios = pd.Series(
            [gain_ratio(data_part, subset_field) * int(len(data_part[subset_field].unique()) != 1)
             for subset_field in subset_fields]).fillna(0)

        self.most_popular_class = data_part.GRADE.mode()[0]
        self.subtrees = {}
        self.partition_field = None
        self.leaf_class = data_part.GRADE.unique()

        if not any(subset_fields_gain_ratios) or len(data_part.GRADE.unique()) == 1:
            self.leaf = True
            return;

        self.partition_field = subset_fields[np.argmax(
            subset_fields_gain_ratios)]

        self.leaf = False
        for partition_field_value in data_part[self.partition_field].unique():
            self.subtrees[partition_field_value] = C4point5(
                data_part[data_part[
                              self.partition_field] == partition_field_value]
                    .drop(columns=self.partition_field))

    def predictMethod(self, data_item):
        if not self.leaf:
            partition_value = data_item[self.partition_field]
            if partition_value in self.subtrees:
                return self.subtrees[partition_value].predictMethod(data_item)
            return self.most_popular_class
        final_class = self.leaf_class
        if len(final_class) == 1:
            return final_class[0]
        return self.most_popular_class
# конец построения



#тестирование дерева
if __name__ == '__main__':
    data_from_csv = pd.read_csv('DATA.csv', index_col='STUDENT ID', sep=';')

    prop_count = int(data_from_csv.shape[1] ** 0.5)
    data = data_from_csv.sample(prop_count, axis=1)
    data['GRADE'] = (data_from_csv.GRADE >= 4).astype(int)

    train, test = train_test_split(data, test_size=0.2)

    tree = C4point5(train)

    test_results = []
    predict_positive = set()
    real_positive = set()
    predict_negative = set()
    real_negative = set()

    for i in range(test.shape[0]):
        predict = tree.predictMethod(test.drop(columns='GRADE').iloc[i])
        if test.iloc[i].GRADE == 1:
            real_positive.add(i)
        else:
            real_negative.add(i)
        test_results.append(predict)
        if predict == 1:
            predict_positive.add(i)
        else:
            predict_negative.add(i)

    true_positive = predict_positive & real_positive
    true_negative = predict_negative & real_negative

    Accuracy = (len(true_positive) + len(true_negative)) / len(test_results)

    print('Accuracy :', Accuracy)

    print(f'Precision для успешных студентов: {len(true_positive) / len(predict_positive)}')
    print(f'Precision для не успешных студентов: {len(true_negative) / len(predict_negative)}')
    print(f'Recall для успешных студентов: {len(true_positive) / len(real_positive)}')
    print(f'Recall для не успешных студентов: {len(true_negative) / len(real_negative)}')



#построение графиков
    #1
    sns.set(font_scale=1.1)
    sns.set_color_codes("deep")

    plt.figure(figsize=(10, 8))
    fpr, tpr, thresholds = roc_curve(test.GRADE.tolist(), test_results)
    plt.plot(fpr, tpr, lw=2, label='ROC кривая')
    plt.plot([0, 1], [0, 1])
    plt.xlim([0.0, 1.0])
    plt.ylim([0.0, 1.0])
    plt.xlabel('False Positive Rate')
    plt.ylabel('True Positive Rate')
    plt.title('ROC curve')
    plt.show()


#2
    precision, recall, thresholds = precision_recall_curve(test.GRADE.tolist(), test_results)

    fig = px.area(
        x=recall, y=precision,
        title=f'Precision-Recall кривая',
        labels=dict(x='Recall', y='Precision'),
        width=700, height=500
    )
    fig.add_shape(
        type='line', line=dict(dash='dash'),
        x0=0, x1=1, y0=1, y1=0
    )
    fig.update_yaxes(scaleanchor="x", scaleratio=1)
    fig.update_xaxes(constrain='domain')

    fig.show()
