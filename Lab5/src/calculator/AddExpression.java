package calculator;

import java.util.ArrayList;
import java.util.List;

class AddExpression extends BinaryExpression {
   public AddExpression(final Expression lft, final Expression rht) {
      super(lft, rht, "+");
   }

   public double _applyOperator(double lft, double rht) {
      return lft + rht;
   }

   public int ar2() {
      List<Object> ar1 = new ArrayList<>();
      ar1.add(5);
      ar1.add(10);
      ar1.add(20);

      ar1.forEach((i) -> {
         i.put(4);
      });

      ar1.forEach(System.out :: print);
      return 0;
   }
}