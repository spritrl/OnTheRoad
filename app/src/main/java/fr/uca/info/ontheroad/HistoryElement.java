package fr.uca.info.ontheroad;

public class HistoryElement {
    private String sectionName;
    private String sectionResult;
    private String sectionIcon;
    private String sectionColor;

    public HistoryElement(String name, String result, String icon, String color){
        this.sectionName = name;
        this.sectionResult = result;
        this.sectionIcon = icon;
        this.sectionColor = color;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionResult() {
        return sectionResult;
    }

    public void setSectionResult(String sectionResult) {
        this.sectionResult = sectionResult;
    }

    public String getSectionIcon() {
        return sectionIcon;
    }

    public void setSectionIcon(String sectionIcon) {
        this.sectionIcon = sectionIcon;
    }

    public String getSectionColor() {
        return sectionColor;
    }

    public void setSectionColor(String sectionColor) {
        this.sectionColor = sectionColor;
    }
}
