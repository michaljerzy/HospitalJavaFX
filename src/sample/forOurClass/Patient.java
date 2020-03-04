package sample.forOurClass;

public class Patient {
    private String PatientID;
    private String FirstName;
    private String Lastname;
    private String Email;
    private String Phone;
    private String StreetAdress;
    private String City;
    private String Voivodeship;
    private String ZipCode;
    private String ChronicDiseases;
    private String PolicyNumber;
    private String Allergies;
    private String RoomID;
    private String OtherHealthConcerns;

    public Patient() {
    }

    public Patient(String patientID, String firstName, String lastname, String email, String phone, String streetAdress, String city, String voivodeship, String zipCode, String chronicDiseases, String policyNumber, String allergies, String roomID, String otherHealthConcerns) {
        PatientID = patientID;
        FirstName = firstName;
        Lastname = lastname;
        Email = email;
        Phone = phone;
        StreetAdress = streetAdress;
        City = city;
        Voivodeship = voivodeship;
        ZipCode = zipCode;
        ChronicDiseases = chronicDiseases;
        PolicyNumber = policyNumber;
        Allergies = allergies;
        RoomID = roomID;
        OtherHealthConcerns = otherHealthConcerns;
    }

    public String getPatientID() {
        return PatientID;
    }

    public void setPatientID(String patientID) {
        PatientID = patientID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getStreetAdress() {
        return StreetAdress;
    }

    public void setStreetAdress(String streetAdress) {
        StreetAdress = streetAdress;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getVoivodeship() {
        return Voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        Voivodeship = voivodeship;
    }

    public String getZipCode() {
        return ZipCode;
    }

    public void setZipCode(String zipCode) {
        ZipCode = zipCode;
    }

    public String getChronicDiseases() {
        return ChronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        ChronicDiseases = chronicDiseases;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public String getAllergies() {
        return Allergies;
    }

    public void setAllergies(String allergies) {
        Allergies = allergies;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getOtherHealthConcerns() {
        return OtherHealthConcerns;
    }

    public void setOtherHealthConcerns(String otherHealthConcerns) {
        OtherHealthConcerns = otherHealthConcerns;
    }
}
