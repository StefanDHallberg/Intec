package intec.inteceksame.entity;

public class Guest {
    private int id;
    private String name;
    private String company;
    private String checkInTime;
    private String saveOption;
    private String selectedLang;


    public String getSelectedLang() {
        return selectedLang;
    }

    public void setSelectedLang(String selectedLang) {
        this.selectedLang = selectedLang;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) { //never used
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { //never used
        this.name = name;
    }

    public String getSaveOption() {
        return saveOption;
    }
    public void setSaveOption(String saveOption) {
        this.saveOption = saveOption;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) { //never used
        this.company = company;
    }
    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }
}
