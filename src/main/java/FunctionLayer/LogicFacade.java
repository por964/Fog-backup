package FunctionLayer;

import DBAccess.DimensionMapper;
import DBAccess.MaterialMapper;
import DBAccess.RequestMapper;
import DBAccess.UserMapper;
import java.util.ArrayList;

/**
 Denne klasse er en del af facade design mønsteret. Metoderne henter data fra databasen via metoder i
 mapper-klasserne til brug på websiderne. Enten som lister eller objekter. Desuden er der
 metoder der opdaterer tabeller i databasen, baseret på bruger input på websiderne.
 */
public class LogicFacade {
    /**Returnerer et user objekt via UserMapper klassen (såfremt credentials er korrekte)
     *
     * @param email brugerens email
     * @param password brugerens password
     * @return User objekt
     * @throws LoginSampleException fejl i login credentials
     */
    public static User login( String email, String password ) throws LoginSampleException {
        return UserMapper.login( email, password );
    }

    /**Læser indholdet af tabellen widths i db og returnerer disse i en liste via MaterialMapper
     *
     * @return ArrayListe med carport bredder
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static ArrayList<Integer> showWidths() throws LoginSampleException, ClassNotFoundException {
        return DimensionMapper.getWidthList();
    }

    /**Læser indholdet af tabellen lengths i db og returnerer disse i en liste via MaterialMapper
     *
     * @return ArrayListe med carport længder
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static ArrayList<Integer> showLengths() throws LoginSampleException, ClassNotFoundException {
        return DimensionMapper.getLengthList();
    }

    /**Læser indholdet af tabellen heights i db og returnerer disse i en liste via MaterialMapper
     *
     * @return ArrayListe med carport højder
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static ArrayList<Integer> showHeights() throws LoginSampleException, ClassNotFoundException {
        return DimensionMapper.getHeightList();
    }

    /**Returnerer et Material objekt fra db via MaterialMapper klassen ud fra parametren ID
     *
     * @param materialId materiale nr
     * @return Material objekt
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static Material showMaterial(int materialId) throws LoginSampleException, ClassNotFoundException {
        return MaterialMapper.getMaterial(materialId);
    }

    /**Læser indholdet af tabellen roofmaterial i db og returnerer disse i en liste via MaterialMapper klassen
     *
     * @param flat tagtype
     * @return ArrayListe med navne på tagmaterialer til tag m/u rejsning
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static ArrayList<String> showRoofMaterialList(boolean flat) throws LoginSampleException, ClassNotFoundException {
        return MaterialMapper.getRoofMaterials(flat);
    }

    /**Læser indholdet af tabellen shedlength i db og returnerer disse i en liste via MaterialMapper klassen
     *
     * @return Liste med skur længder
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static ArrayList<Integer> showShedLengths() throws LoginSampleException, ClassNotFoundException {
        return DimensionMapper.getShedLengthList();
    }

    /**Læser indholdet af tabellen shedwidth i db og returnerer disse i en liste via MaterialMapper klassen
     *
     * @return Liste med skur bredder
     * @throws LoginSampleException fejl i login credentials
     * @throws ClassNotFoundException
     */
    public static ArrayList<Integer> showShedWidths() throws LoginSampleException, ClassNotFoundException {
        return DimensionMapper.getShedWidthList();
    }

    /**Tager et customerrequest objekt som parameter og indsætter dette i tabellen cust_request i db via RequestMapper klassen
     *
     * @param request nr
     * @throws LoginSampleException fejl i login credentials
     */
    public static void createRequest(CustomerRequest request) throws LoginSampleException {
        RequestMapper.createRequest(request);
    }

    /**Henter CustomerRequest objekter i db cust_request via RequestMapper hvor processed er = false og
     *
     * @return Liste med CustomerRequest objekter
     * @throws LoginSampleException fejl i login credentials
     */
    public static ArrayList<CustomerRequest> showRequests(boolean done) throws LoginSampleException {
        return RequestMapper.showNewRequests(done);
    }

    /**Henter og returnerer et CustomerRequest objekt fra db via Requestmapper ud fra parametren ID
     *
     * @param id request nr
     * @return CustomerRequest objekt
     * @throws LoginSampleException fejl i login credentials
     */
    public static CustomerRequest showRequest(int id) throws LoginSampleException {
        return RequestMapper.getRequestFromID(id);
    }

    /**Opdaterer prisen på en kundeforespørgsel i tabellen cust_request med et ID som parameter og
     * sætter processed=true  via RequestMapper.
     *
     * @param price request pris
     * @param id request id
     * @throws LoginSampleException fejl i login credentials
     */
    public static void updateRequest(double price, int id) throws LoginSampleException {
        RequestMapper.processRequest(price,id);
    }
    /**Opdaterer prisen på en kundeforespørgsel i tabellen cust_request med et ID som parameter, via RequestMapper.
     *
     * @param price request pris
     * @param id request id
     * @throws LoginSampleException fejl i login credentials
     */
    public static void updatePrice(double price, int id) throws LoginSampleException {
        RequestMapper.setPrice(price,id);
    }

}