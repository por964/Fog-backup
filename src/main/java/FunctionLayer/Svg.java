package FunctionLayer;

/**
 * Formålet med denne klasse er at skabe en SVG tegning ud fra valgte carportdimensioner.
 * SVG tegningen sendes som en String til html siden og vises på websiden.
 */

public class Svg {

    private int width;
    private int height;
    private String viewbox;
    private int x;
    private int y;
    private int x2;
    private int y2;
    private StringBuilder svg = new StringBuilder();

    private final String headerTemplate = "<svg version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" height=\"%s\" width=\"%s\" viewBox=\"%s\" preserveAspectRatio=\"xMinYMin\">";
    private final String rectTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String strapTemplate = "<rect x=\"%d\" y=\"%d\" height=\"%d\" width=\"%d\" style=\"stroke:#000000; fill: #ffffff\" />";
    private final String crossTemplate = "<line x1=\"%d\" y1=\"%d\" x2=\"%d\" y2=\"%d\" style=\"stroke:#000000; stroke-dasharray: 5 5;\" />";

    public Svg(int width, int height, String viewbox, int x, int y) {
        this.width = width;
        this.height = height;
        this.viewbox = viewbox;
        this.x = x;
        this.y = y;
        svg.append(String.format(headerTemplate, height, width, viewbox));
    }

    public void addRect(int x, int y, int height, int width){
        svg.append(String.format(rectTemplate, x, y, height, width));
    }

    public void addStrap(int x, int y, int height, int width){
        svg.append(String.format(strapTemplate, x, y, height, width));
    }

    public void addCross(int x, int y,int x2, int y2){
        svg.append(String.format(crossTemplate, x, y, x2, y2));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getViewbox() {
        return viewbox;
    }

    public void setViewbox(String viewbox) {
        this.viewbox = viewbox;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX1() { return x2; }

    public void setX1(int x1) {
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public String toString() {
        return svg.toString() + "</svg>" ;
    }
}
