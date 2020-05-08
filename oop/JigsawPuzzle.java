import java.util.List;
import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collection;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Piece {
  Edge top;
  Edge bottom;
  Edge left;
  Edge right;

  List<Edge> getEdges() { return List.of(top, right, bottom, left); }

  static Piece create(Edge top, Edge right, Edge bottom, Edge left) {
    Piece p = new Piece();
    p.top = top;
    p.right = right;
    p.bottom = bottom;
    p.left = left;
    return p;
  }

  public boolean isCorner() {
    int count = 0;
    if (this.top == Edge.BLANK) count++;
    if (this.bottom == Edge.BLANK) count++;
    if (this.left == Edge.BLANK) count++;
    if (this.right == Edge.BLANK) count++;
    return count >= 2;
  }

  public static List<Piece> create(int width, int height) {
    List<List<Piece>> pieces = new ArrayList<>(height);
    for (int i = 0; i < height; i++)
      pieces.add(new ArrayList<>(width));
    
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Edge top, right, bottom, left;
        if (row == 0) {
          top = Edge.BLANK;
        } else {
          top = new Edge(
                    new StringBuilder(
                        pieces.get(row-1).get(col).bottom.signature
                    ).reverse().toString()
                );
        }
        right = col == width-1 ? Edge.BLANK : new Edge(UUID.randomUUID().toString());
        bottom = row == height-1 ? Edge.BLANK : new Edge(UUID.randomUUID().toString());
        if (col == 0) {
          left = Edge.BLANK;
        } else {
          left = new Edge(
                     new StringBuilder(
                         pieces.get(row).get(col-1).right.signature
                     ).reverse().toString()
                 );
        }
        pieces.get(row).add(Piece.create(top, right, bottom, left));
      }
    }
    return pieces.stream().flatMap(List::stream).collect(Collectors.toList());
  }

  @Override
  public String toString() {
    return String.format("Piece: {\n\t%s,\n\t%s,\n\t%s,\n\t%s\n}\n",
                      top.signature,
                      right.signature,
                      bottom.signature,
                      left.signature);
  }

  private Piece() {}
}

class Edge {
  static final Edge BLANK = new Edge("BLANK");

  String signature;

  public Edge(String signature) { this.signature = signature; }

  static boolean fitsWith(Edge e1, Edge e2) {
    return new StringBuilder(e1.signature).reverse().toString().equals(e2.signature);
  }
}

class Grid {
  LinkedList<Cell> cells = new LinkedList<>();
  
  private Grid() {}

  public Cell getFirst() {
    return cells.getFirst();
  }

  public static Grid create(int width, int height) {
    Grid g = new Grid();
    Cell prevRow = null;
    for (int row = 0; row < height; row++) {
      Cell currCell = null;
      Cell prevCell = null;
      for (int col = 0; col < width; col++) {
        currCell = new Cell();
        g.cells.add(currCell);
        if (prevRow != null) {
          prevRow.onBottom(currCell);
          prevRow = prevRow.right;
        }
        if (prevCell != null) prevCell.onRight(currCell);
        prevCell = currCell;
      }
      while (prevCell.left != null) prevCell = prevCell.left;
      prevRow = prevCell;
    }
    return g;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Cell row = cells.iterator().next();
    while (row != null) {
      Cell col = row;
      while (col != null) {
        sb.append(col);
        sb.append(", ");
        col = col.right;
      } 
      sb.append("\n");
      row = row.bottom;
    }
    return sb.toString();
  }
}

class Cell {
  Piece piece;
  Edge topEdge;
  Edge bottomEdge;
  Edge leftEdge;
  Edge rightEdge;
  Cell top;
  Cell bottom;
  Cell left;
  Cell right;

  public Piece onlyOneMatch(Collection<Piece> pieces) {
    Piece match = null;
    for (Piece piece : pieces) {
      if (this.match(piece)) {
        if (match == null) {
          match = piece;
        } else {
          return null;
        }
      }
    }
    return match;
  }

  public int getScore() {
    int score = 0;
    if (top != null && top.piece != null) score += 3;
    if (right != null && right.piece != null) score += 3;
    if (bottom != null && bottom.piece != null) score += 3;
    if (left != null && left.piece != null) score += 3;
    if (top == null) score += 1;
    if (right == null) score += 1;
    if (bottom == null) score += 1;
    if (left == null) score += 1;
    return score;
  }

  public boolean hasNeighborsNumber(int number) {
    int count = 0;
    if (isBusy(top)) count++;
    if (isBusy(right)) count++;
    if (isBusy(bottom)) count++;
    if (isBusy(left)) count++;
    return count == number;
  }

  private boolean isBusy(Cell cell) {
    return cell == null || cell.piece != null;
  }

  void onBottom(Cell cell) {
    this.bottom = cell;
    cell.top = this;
  } 

  void onRight(Cell cell) {
    this.right = cell;
    cell.left = this;
  }

  boolean fitsBottom(Edge edge) {
    return fits(this.bottomEdge, edge);
  }

  boolean fitsLeft(Edge edge) {
    return fits(this.leftEdge, edge);
  }

  boolean fitsRight(Edge edge) {
    return fits(this.rightEdge, edge);
  }

  boolean fitsTop(Edge edge) {
    return fits(this.topEdge, edge);
  }

  private boolean fits(Edge innerEdge, Edge outerEdge) {
    return innerEdge == null || Edge.fitsWith(outerEdge, innerEdge);
  }

  public void put(Piece piece) {
    this.piece = piece;
    LinkedList<Edge> edges = new LinkedList<>(piece.getEdges());

    boolean fits = false;
    int tries = 0;
    while (!fits) {
      fits = true;
      if (top == null)
        fits &= edges.get(0) == Edge.BLANK;
      else
        fits &= top.fitsBottom(edges.get(0));
      if (right == null)
        fits &= edges.get(1) == Edge.BLANK;
      else
        fits &= right.fitsLeft(edges.get(1));
      if (bottom == null)
        fits &= edges.get(2) == Edge.BLANK;
      else
        fits &= bottom.fitsTop(edges.get(2));
      if (left == null)
        fits &= edges.get(3) == Edge.BLANK;
      else
        fits &= left.fitsRight(edges.get(3));
      if (fits) {
        this.topEdge = edges.get(0);
        this.rightEdge = edges.get(1);
        this.bottomEdge = edges.get(2);
        this.leftEdge = edges.get(3);
      } else {
        rotate(edges);
      }
      tries++;
      if (tries > 4) throw new IllegalStateException("Piece does not fit.");
    }
  }

  public boolean match(Piece piece) {
    this.piece = piece;
    LinkedList<Edge> edges = new LinkedList<>(piece.getEdges());

    boolean fits = false;
    int tries = 0;
    while (!fits) {
      fits = true;
      if (top == null)
        fits &= edges.get(0) == Edge.BLANK;
      else
        fits &= top.fitsBottom(edges.get(0));
      if (right == null)
        fits &= edges.get(1) == Edge.BLANK;
      else
        fits &= right.fitsLeft(edges.get(1));
      if (bottom == null)
        fits &= edges.get(2) == Edge.BLANK;
      else
        fits &= bottom.fitsTop(edges.get(2));
      if (left == null)
        fits &= edges.get(3) == Edge.BLANK;
      else
        fits &= left.fitsRight(edges.get(3));
      if (fits) {
        return true;
      } else {
        rotate(edges);
      }
      tries++;
      if (tries > 4) return false;
    }
    return false;
  }

  private static void rotate(LinkedList<Edge> edges) {
    edges.addFirst(edges.removeLast());
  }

  @Override
  public String toString() {
    return piece == null ? "" : piece.toString();
  }
}

public class JigsawPuzzle {
  static Grid solve(Grid grid, List<Piece> pieces) {
    Optional<Piece> corner = pieces.stream().filter(Piece::isCorner).findAny();
    grid.getFirst().put(corner.get());
    pieces.remove(corner.get());

    while (!pieces.isEmpty()) {
      List<Cell> cells = grid.cells.stream()
                             .filter(c -> c.piece == null)
                             .sorted(Comparator.comparingInt(Cell::getScore).reversed())
                             .collect(Collectors.toList());

      boolean removed = false;
      for (Cell cell : cells) {
        Piece piece;
        if ((piece = cell.onlyOneMatch(pieces)) != null) {
          cell.put(piece);
          pieces.remove(piece);
          removed = true;
          break;
        }
      }
      if (!removed) {
        throw new IllegalStateException("Not supported yet.");
      }
      // if none match uniquely same scenarios but recurse to handle ambiguity
    }
    return grid;
  }

  public static void main(String[] args) {
    Grid g = Grid.create(10, 10);
    List<Piece> pieces = Piece.create(10, 10);
    Collections.shuffle(pieces);
    g = solve(g, pieces);
    System.out.println(g);
  }
}

