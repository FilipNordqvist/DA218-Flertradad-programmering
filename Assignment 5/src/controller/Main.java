package controller;

public class Main {

    public static void main(String[] args) {
        new Controller();
    }

    /*
 public static void main(String[] args) {
        PatientManagementSystem system = new PatientManagementSystem();

        // Add a patient asynchronously
        Future<String> addPatientResult = system.addPatient(new Patient("P001", "John Doe", 35, "Male"));

        // Update a patient asynchronously
        Future<String> updatePatientResult = system.updatePatient(new Patient("P001", "John Doe", 36, "Male"));

        // Get patient details asynchronously
        Future<Patient> getPatientResult = system.getPatient("P001");

        // Search for patients by name asynchronously
        Future<List<Patient>> searchPatientsResult = system.searchPatientsByName("John Doe");

        // Delete a patient asynchronously
        Future<String> deletePatientResult = system.deletePatient("P001");

        try {
            System.out.println(addPatientResult.get());
            System.out.println(updatePatientResult.get());
            System.out.println(getPatientResult.get());
            System.out.println(searchPatientsResult.get());
            System.out.println(deletePatientResult.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        system.shutdown();
    }

     */
}