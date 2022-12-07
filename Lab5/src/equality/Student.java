package equality;

import java.util.List;
import java.util.Objects;

class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }
   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Student) {
         Student s1 = (Student) obj;
         return (this.surname == s1.surname) && (this.givenName == s1.givenName) && (this.age == s1.age) &&
                 (this.currentCourses == s1.currentCourses);
      }
      return false;
   }

   public int hashCode() {
      return Objects.hash(surname, givenName, age, currentCourses) + 31;
   }
}
