package submit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Code formatter project
 * CS 4481
 */
/**
 *
 */
public class SymbolTable {

  private final HashMap<String, SymbolInfo> table;
  private SymbolTable parent;
  private final List<SymbolTable> children;
  private static int labelCounter = 0; // global across machine code
  private int localOffset = 0;
  private int childIndex = 0;

  public SymbolTable() {
    table = new HashMap<>();
    parent = null;
    children = new ArrayList<>();

    this.addSymbol("println", new SymbolInfo("println", null, true));
  }

  private SymbolTable(SymbolTable parent){
    table = new HashMap<>();
    this.parent = parent;
    this.children = new ArrayList<>();
  }

  public SymbolTable getNextChild() {
    if (childIndex < children.size()){
      return children.get(childIndex++);
    }
    return this;
  }

  public int getNextOffset() {
    int current = localOffset;
    localOffset += 4;
    return current;
  }

  public String getUniqueLabel() {
    return "strLabel" + (labelCounter++);
  }

  public void addSymbol(String id, SymbolInfo symbol) {
    table.put(id, symbol);
  }

  /**
   * Returns null if no symbol with that id is in this symbol table or an
   * ancestor table.
   *
   * @param id
   * @return
   */
  public SymbolInfo find(String id) {
    if (table.containsKey(id)) {
      return table.get(id);
    }
    if (parent != null) {
      return parent.find(id);
    }
    return null;
  }

  /**
   * Returns the new child.
   *
   * @return
   */
  public SymbolTable createChild() {
    SymbolTable child = new SymbolTable(this);
    children.add(child);
    return child;
  }

  public SymbolTable getParent() {
    return parent;
  }



}
