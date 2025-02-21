public abstract class Person implements Comparable<Person> {
    private String name;

    public Person() {
        name = "Jane Doe";
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public boolean equals(Person person) {
        return name.equals(person.getName());
    }

    public String toString() {
        return name;
    }

}
