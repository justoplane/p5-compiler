/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author edwajohn
 */
public class Call extends AbstractNode implements Expression {

  private final String id;
  private final List<Expression> args;

  public Call(String id, List<Expression> args) {
    this.id = id;
    this.args = new ArrayList<>(args);
  }

  @Override
  public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {

    if (id.equals("println")){
      Expression arg = args.get(0);
      MIPSResult argResult = arg.toMIPS(code, data, symbolTable, regAllocator);

      if (argResult.getType() == VarType.INT){
        code.append("  li $v0, 1\n");
        code.append("  move $a0, ").append(argResult.getRegister()).append("\n");
        code.append("  syscall\n");

        regAllocator.clear(argResult.getRegister());
      } else {
        code.append("  li $v0, 4\n");
        code.append("  la $a0, ").append(argResult.getAddress()).append("\n");
        code.append("  syscall\n");
      }

      code.append(" li $v0, 11\n");
      code.append(" li $a0, 10\n");
      code.append(" syscall\n");

      return MIPSResult.createVoidResult();
    }

    return MIPSResult.createVoidResult();
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(id).append("(");
    for (Expression arg : args) {
      arg.toCminus(builder, prefix);
      builder.append(", ");
    }
    if (!args.isEmpty()) {
      builder.setLength(builder.length() - 2);
    }
    builder.append(")");
  }

}
