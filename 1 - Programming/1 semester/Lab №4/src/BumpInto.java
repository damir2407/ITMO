public class BumpInto {
    private static String name = "наткнуться";

    public void bumpInto(Person person, Place place, Surface surface) {
        person.setPosition(place);
        System.out.println(person.getName() + " наткнулся(ась) на " + surface.getCondition() + " " + surface.getName());
        System.out.println("текущее местоположение " + person.getName() + " " + place.getName());
    }
}
