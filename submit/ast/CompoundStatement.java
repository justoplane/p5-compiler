/*
 * Code formatter project
 * CS 4481
 */
package submit.ast;

import submit.MIPSResult;
import submit.RegisterAllocator;
import submit.SymbolTable;

import java.util.List;

/**
 *
 * @author edwajohn
 */
public class CompoundStatement extends AbstractNode implements Statement {

  private final List<Statement> statements;
  private SymbolTable scope;

  public CompoundStatement(List<Statement> statements) {
    this.statements = statements;
  }

  public void setScope(SymbolTable scope){
    this.scope = scope;
  }

  @Override
  public void toCminus(StringBuilder builder, String prefix) {
    builder.append(prefix).append("{\n");
    for (Statement s : statements) {
      s.toCminus(builder, prefix + "  ");
    }
    builder.append(prefix).append("}\n");
  }

  @Override
  public MIPSResult toMIPS(StringBuilder code, StringBuilder data, SymbolTable symbolTable, RegisterAllocator regAllocator) {

    SymbolTable localScope = symbolTable.getNextChild();

    for (Statement s : statements){
      s.toMIPS(code, data, localScope, regAllocator);
    }
    return MIPSResult.createVoidResult();
  }
}
