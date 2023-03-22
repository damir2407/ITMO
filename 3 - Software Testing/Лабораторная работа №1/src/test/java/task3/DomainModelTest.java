package test.java.task3;

import main.java.task3.act.Skill;
import main.java.task3.being.*;
import main.java.task3.exception.DescException;
import main.java.task3.notalive.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class DomainModelTest {


    @Test
    @DisplayName("Тест для проверки Хемулихи, ее навыков и локации")
    public void hemulihaTest() {
        Hemuliha hemuliha = new Hemuliha();
        Skill skill = new Skill("угощать ");
        Guests guests = new Guests();
        Home home = new Home();
        Tea tea = new Tea();

        Assertions.assertNull(hemuliha.getDescriptions(), "У Хемулихи пока не может быть описания!");
        hemuliha.setDescriptions(Size.LITTLE);
        Assertions.assertEquals(Size.LITTLE, hemuliha.getDescriptions(), "Хемулиха должна быть маленькой!");

        hemuliha.setDescriptions(Size.BIG);
        Assertions.assertEquals(Size.BIG, hemuliha.getDescriptions(), "Хемулиха должна быть большой!");

        Assertions.assertEquals(List.of(), hemuliha.getSkills(), "У Хемулихи пока не должно быть навыков!");
        hemuliha.learn(skill);
        Assertions.assertEquals(List.of(skill), hemuliha.getSkills(), "У Хемулихи должен быть навык 'угощать'!");

        hemuliha.setWhoInteractsWith(guests);
        Assertions.assertEquals(guests.getName(), hemuliha.getWhoInteractsWith().getName(), "Хемулиха должна взаимодействовать с гостями!");

        hemuliha.setWhere(home);
        Assertions.assertEquals(home.getName(), hemuliha.getWhere().getName(), "Хемулиха должна находится дома");

        hemuliha.setHave(tea);
        Assertions.assertEquals(tea.getName(), hemuliha.getHave().getName(), "У Хемулихи должен быть чай");

        Assertions.assertEquals("большая Хемулиха угощать гостей чаем у себя дома",
                hemuliha.getDescriptions().getDescription() + hemuliha.getName() + hemuliha.getSkills().get(0).getName() +
                        hemuliha.getWhoInteractsWith().getName() + hemuliha.getHave().getName() +
                        hemuliha.getWhere().getDescription());

    }

    @Test
    @DisplayName("Проверка сущностей на изучение различных навыков")
    public void skillsTest() {
        Snusmumrik snusmumrik = new Snusmumrik();
        Skill skill2 = new Skill("любить");
        Skill skill = new Skill("угощать ");
        Skill skill3 = new Skill("Получить");
        Skill skill4 = new Skill("Вручить");
        Kids kid = new Kids();

        Assertions.assertEquals(List.of(), snusmumrik.getSkills(), "Снюсмумрик не должен иметь навыков!");
        Assertions.assertEquals(List.of(), kid.getSkills(), "Малыш не должен иметь навыков!");
        snusmumrik.learn(skill2);
        snusmumrik.learn(skill3);
        kid.learn(skill4);
        Assertions.assertEquals(List.of(skill4), kid.getSkills(), "Малыш должен владеть навыком 'вручить'!");
        Assertions.assertEquals(List.of(skill2, skill3), snusmumrik.getSkills(), "Снюсмумрик должен владеть навыками 'любить' и 'получить'!");
        Assertions.assertNotEquals(List.of(skill, skill2, skill4), snusmumrik.getSkills(), "Снюс мумрик не должен владеть навыками 'любить','угощать' и 'вручить'!");
    }


    @Test
    @DisplayName("Проверка афиши и места, над которым он кружится")
    public void posterSpinTest() {
        Poster poster = new Poster();
        Place forest = new Forest();
        Assertions.assertNull(poster.getWhereSpin(), "Афиша не должна крутиться!");

        Assertions.assertThrows(DescException.class, forest::getInfo);
        forest.setDescription("над");

        Assertions.assertDoesNotThrow(() -> {
            forest.getInfo();
        });
        poster.setWhereSpin(forest);

        Assertions.assertEquals("продолжали кружиться над лесом.", poster.continued());

        Assertions.assertEquals("спланировала на лесную полянку", poster.planning());
    }


    @Test
    @DisplayName("Проверка афиши на прилипаемость к крыше до и после осмоления")
    public void posterClingTest() {
        Poster poster = new Poster();
        Place roof = new Roof("крышу", "обычную");

        poster.setWhereCling(roof);
        Assertions.assertNull(poster.getWhereCling(), "Афиша не должна прилипнуть к обычной крыше!");
        roof.setDescription("просмоленная");
        poster.setWhereCling(roof);
        Assertions.assertEquals("просмоленная крышу", poster.getWhereCling().getDescription(), "Афиша должна прилипнуть к просмоленной крыше!");
    }

    @Test
    @DisplayName("Проверка малыша на возможность взять постер на крыше")
    public void childClimbTest() {
        Place roof = new Roof("крыша", "просмоленная");
        Poster poster = new Poster();
        poster.setWhereCling(roof);

        Kids kid = new Kids();
        kid.setGivePoster(poster);
        Assertions.assertNull(kid.getGiveWhat(), "Малыш не может взать афишу, т.к он не на крыше!");
        kid.setWhereClimbed(roof);
        kid.setGivePoster(poster);
        Assertions.assertEquals("малыш взял постер", kid.getName() + " взял " + kid.getGiveWhat().getName(), "Малыш должен взять афишу, т.к он на крыше!");
    }

    @Test
    @DisplayName("Проверка количества клочков бумаги взависимости от ее типа")
    public void paperPiecesTest() {
        Place roof = new Roof("крыша", "просмоленная");
        Poster poster = new Poster();
        poster.setWhereCling(roof);

        ArrayDeque<Born> bornQueue = new ArrayDeque<>();
        for (int i = 1; i < 25; i++) {
            bornQueue.add(new Kids("малыш ", "по номеру" + i));
        }
        poster.setDescription("обычный");
        Assertions.assertEquals(bornQueue.peek().getDescription() + " забирает 1 клочок бумаги",
                poster.creatureWantToTake(bornQueue) + " забирает " + poster.getDescription(), "Т.к афиша обычная ее может забрать лишь 1 малыш!");

        poster.setDescription("тонкий");
        poster.creatureWantToTake(bornQueue);
        Assertions.assertEquals("превратилась в 24 клочка бумаги", poster.getDescription(), "Т.к афиша тонкая ее должно разорвать на клочки!");

    }


}
