/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

/**
 *
 * @author edwajohn
 */
public class BinaryOperator extends AbstractNode implements Expression {

  private final Expression lhs, rhs;
  private final BinaryOperatorType type;

  public BinaryOperator(Expression lhs, BinaryOperatorType type, Expression rhs) {
    this.lhs = lhs;
    this.type = type;
    this.rhs = rhs;
  }

  public BinaryOperator(Expression lhs, String type, Expression rhs) {
    this.lhs = lhs;
    this.type = BinaryOperatorType.fromString(type);
    this.rhs = rhs;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    lhs.toCminus(builder, prefix);
    builder.append(" ").append(type).append(" ");
    rhs.toCminus(builder, prefix);
  }

  @Override
  public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {
    MIPSResult leftResult = lhs.toMIPS(code, data, symbolTable, regAllocator);
    MIPSResult rightResult = rhs.toMIPS(code, data, symbolTable, regAllocator);

    String leftReg = leftResult.getRegister();
    String rightReg = rightResult.getRegister();

    String resultReg = regAllocator.getT();

    switch (type) {
      case PLUS:
        code.append("  add ").append(resultReg).append(", ").append(leftReg).append(rightReg).append("\n");
        break;
      case MINUS:
        code.append("  sub ").append(resultReg).append(", ").append(leftReg).append(rightReg).append("\n");
        break;
      case TIMES:
        code.append("  mul ").append(resultReg).append(", ").append(leftReg).append(rightReg).append("\n");
        break;
      case DIVIDE:
        code.append("  div ").append(leftReg).append(", ").append(rightReg).append("\n");
        code.append("  mflo ").append(resultReg).append("\n");
        break;
      default:
        break;
    }

    regAllocator.clear(leftReg);
    regAllocator.clear(rightReg);

    return MIPSResult.createRegisterResult(resultReg, VarType.INT);
  }
}
