public class SimpleIterationFunctional {
    private Messenger messenger;

    public SimpleIterationFunctional(Messenger messenger) {
        this.messenger = messenger;
    }

    public void simpleIterationMethod(MatrixPOJO userMatrix) {

        MatrixPOJO finalUserMatrix;
        if (!checkDiagonalDominance(userMatrix)) {
            MatrixPOJO changedMatrix = changeRows(userMatrix);

            if (!checkDiagonalDominance(changedMatrix)) {
                messenger.diagonalDominatingIsMissingMessage();
                finalUserMatrix = userMatrix;
            } else {
                finalUserMatrix = changedMatrix;
                messenger.newChangedMatrixMessage();
                messenger.introducedMatrixMessage(finalUserMatrix.getMatrix());
            }

        } else finalUserMatrix = userMatrix;

        iterate(finalUserMatrix);


    }

    public boolean checkDiagonalDominance(MatrixPOJO userMatrix) {
        try {
            double rowSum = 0;
            int strictInequalityCounter = 0;
            for (int i = 0; i < userMatrix.getSize(); i++) {
                for (int j = 0; j < userMatrix.getSize(); j++) {
                    if (i != j) {
                        rowSum += Math.abs(userMatrix.getMatrix()[i][j]);
                    }
                }
                if (!(Math.abs(userMatrix.getMatrix()[i][i]) >= rowSum)) {
                    return false;
                }
                if (Math.abs(userMatrix.getMatrix()[i][i]) > rowSum) {
                    strictInequalityCounter++;
                }
                rowSum = 0;

            }

            if (strictInequalityCounter == 0) return false;
        } catch (NullPointerException nullPointerException) {
            return false;
        }
        return true;
    }

    public MatrixPOJO changeRows(MatrixPOJO userMatrix) {
        double maxElement = -10000;
        int maxElementColumnIndex = 0;
        Double[][] changedMatrix = new Double[userMatrix.getSize()][userMatrix.getSize() + 1];
        for (int i = 0; i < userMatrix.getSize(); i++) {
            for (int j = 0; j < userMatrix.getSize(); j++) {
                if (userMatrix.getMatrix()[i][j] > maxElement) {
                    maxElement = userMatrix.getMatrix()[i][j];
                    maxElementColumnIndex = j;
                }
            }
            int k = 0;
            while (k < userMatrix.getSize() + 1) {
                changedMatrix[maxElementColumnIndex][k] = userMatrix.getMatrix()[i][k];
                k++;
            }
            maxElement = 0;
            maxElementColumnIndex = 0;
        }
        return new MatrixPOJO(changedMatrix, userMatrix.getAccuracy(), userMatrix.getSize());
    }

    public void iterate(MatrixPOJO userMatrix) {
        double previousErrorEstimate = 0;

        Double[] results = new Double[userMatrix.getSize()];
        for (int i = 0; i < userMatrix.getSize(); i++) {
            for (int j = 0; j < userMatrix.getSize() + 1; j++) {
                results[i] = userMatrix.getMatrix()[i][j];
            }
        }

        Double[] initialApproximation = new Double[userMatrix.getSize()];
        for (int i = 0; i < userMatrix.getSize(); i++) {
            initialApproximation[i] = 0.0;
        }

        int numberOfIterations = 0;
        Double[] newInitialApproximation = new Double[userMatrix.getSize()];

        while (true) {
            messenger.numberOfIterationMessage(numberOfIterations);
            double formula = 0;
            for (int i = 0; i < userMatrix.getSize(); i++) {
                for (int j = 0; j < userMatrix.getSize(); j++) {
                    if (i != j) {
                        formula = formula + (-userMatrix.getMatrix()[i][j] * initialApproximation[j]);
                    } else continue;
                }
                int indexOfX = i + 1;
                formula = (results[i] + formula) / userMatrix.getMatrix()[i][i];
                messenger.computedXMessage(indexOfX, formula);
                newInitialApproximation[i] = formula;
                formula = 0;
            }

            if (numberOfIterations != 0) {
                double maxOfErrorVector = Math.abs(newInitialApproximation[0] - initialApproximation[0]);
                for (int i = 0; i < userMatrix.getSize(); i++) {
                    double def = Math.abs(newInitialApproximation[i] - initialApproximation[i]);
                    if (def > maxOfErrorVector) {
                        maxOfErrorVector = def;
                    }
                }
                messenger.currentErrorEstimateMessage(maxOfErrorVector);
                if (previousErrorEstimate != 0 && maxOfErrorVector > previousErrorEstimate) {
                    System.out.println("Погрешность увеличилась!");
                    return;
                }
                previousErrorEstimate = maxOfErrorVector;
                if (maxOfErrorVector <= userMatrix.getAccuracy()) return;
            }
            numberOfIterations++;
            for (int i = 0; i < userMatrix.getSize(); i++) {
                initialApproximation[i] = newInitialApproximation[i];
            }


        }


    }

}
