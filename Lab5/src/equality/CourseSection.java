package equality;

import java.time.LocalTime;
import java.util.Objects;

class CourseSection {
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
                        final int enrollment, final LocalTime startTime, final LocalTime endTime) {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj instanceof CourseSection) {
         CourseSection c1 = (CourseSection) obj;
         return this.prefix != null && c1.prefix != null && this.prefix.equals(c1.prefix)
                 && this.number != null && c1.number != null && this.number.equals(c1.number)
                 && (this.enrollment == c1.enrollment)
                 && this.startTime != null && c1.startTime != null && this.startTime.equals(c1.startTime)
                 && this.endTime != null && c1.endTime != null && this.endTime.equals(c1.endTime);
      }
      return false;
   }

   public int hashCode() {
      return prefix.hashCode() + number.hashCode() + enrollment + startTime.hashCode() + endTime.hashCode() + 31;
   }
}

   // additional likely methods not defined since they are not needed for testing
