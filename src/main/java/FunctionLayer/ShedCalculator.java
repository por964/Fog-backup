package FunctionLayer;

import java.util.ArrayList;
import static FunctionLayer.Calculator.bom;

/**
 * Denne klasse tilføjer et skur til materalelisten hvis kunden har valgt værdier for skur (ikke nul)
 */

public class ShedCalculator {

    //Instantiering af variable der skal bruges i addMaterial() metoder:
    int widthShed;
    int lengthShed;
    int carportHeight;
    int convertMM2ToM2 = 1000000;
    int materialId;
    int minimumQuantity = 1;
    int sizeOfEach;
    int unitsPerPack;
    int calculatedQuantity;

    public ArrayList<Material> shedBomCalculator (int height, int shedWidth, int shedLength) throws LoginSampleException, ClassNotFoundException {

        //Erklærer variable til addMaterial metoderne:
        carportHeight = height;
        widthShed = shedWidth;
        lengthShed = shedLength;

        //Tilføjer materialer til skur:
        addMaterial4();
        addMaterial31();
        addMaterial32();
        addMaterial33();

        return bom;
    }

    //Materiale med ID nr. 4 bliver tilføjet:
    public void addMaterial4 () throws LoginSampleException, ClassNotFoundException {
        materialId = 4;
        int noOfSides = 2;
        sizeOfEach = 60;
        int fixedNum = 200;

        Material mat = LogicFacade.showMaterial(materialId);

        mat.setLength(carportHeight-fixedNum);
        mat.setQuantity(((lengthShed/sizeOfEach)*noOfSides) + ((widthShed/sizeOfEach)*noOfSides));
        bom.add(mat);
    }

    //Materiale med ID nr. 31 bliver tilføjet:
    public void addMaterial31 () throws LoginSampleException, ClassNotFoundException {
        materialId = 31;
        minimumQuantity = 4;

        Material mat = LogicFacade.showMaterial(materialId);

        mat.setQuantity(((lengthShed*widthShed)/convertMM2ToM2)+minimumQuantity);

        bom.add(mat);
    }

    public void addMaterial32 () throws LoginSampleException, ClassNotFoundException {
        materialId = 32;
        sizeOfEach = 60;
        int fixedNum = 2;
        unitsPerPack = 400;
        minimumQuantity = 1;

        Material mat = LogicFacade.showMaterial(materialId);

        calculatedQuantity = (((lengthShed/sizeOfEach)*fixedNum) + ((widthShed/sizeOfEach)/fixedNum))/unitsPerPack;

        //Sikrer et minumum antal af enheden:
        if (calculatedQuantity > minimumQuantity) {
            mat.setQuantity(calculatedQuantity);
        } else {
            mat.setQuantity(minimumQuantity);
        }

        bom.add(mat);
    }

    public void addMaterial33 () throws LoginSampleException, ClassNotFoundException {
        materialId = 33;
        int sizeOfEach = 60;
        int fixedNum = 2;
        unitsPerPack = 300;

        Material mat = LogicFacade.showMaterial(materialId);

        calculatedQuantity = ((((lengthShed/sizeOfEach)*fixedNum) + (((widthShed/sizeOfEach)*fixedNum))/fixedNum)*fixedNum)/unitsPerPack;

        //Sikrer et minumum antal af enheden:
        if (calculatedQuantity > minimumQuantity) {
            mat.setQuantity(calculatedQuantity);
        } else {
            mat.setQuantity(minimumQuantity);
        }

        bom.add(mat);
    }


}
