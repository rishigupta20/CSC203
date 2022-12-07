class Point
{
   public final double x;
   public final double y;
   public final double z;

   public Point(double x, double y, double z)   {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public String toString()   {
      return "(" + x + "," + y + ")";
   }

   public boolean equals(Object other)   {
      return other instanceof Point &&
         ((Point)other).x == this.x &&
         ((Point)other).y == this.y &&
              ((Point)other).z == this.z;
   }

   public int hashCode()   {
      int result = 17;
      result = (int) (result * 31 + x);
      result = (int) (result * 31 + y);
      result = (int) (result * 31 + z);
      return result;
   }

   public boolean adjacent(Point p)   {
      return (x == p.x && Math.abs(y - p.y) == 1) ||
              (y == p.y && Math.abs(x - p.x) == 1) ||
              (z == p.z && Math.abs(z - p.z) == 1);
   }

   public double getX(){
      return this.x;
   }
   public double getY() {
      return this.y;
   }
   public double getZ() {
      return this.z;
   }
}