package com.company.presentation;

import com.company.dao.IDao;
import com.company.metier.IMetier;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation {

    public static void main(String[] args) throws FileNotFoundException {

        //injection des dependances
        /*
        DaoImpl dao=new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);
        System.out.println(metier.calcul());*/

        //version dynamique
        try {
            Scanner scanner = new Scanner(new File("src/config.txt"));
            String daoClassName= scanner.next();
            String metierClassName= scanner.next();
            //System.out.println(daoClassName);
            //System.out.println(metierClassName);

            //instanciation dynamique : charger la classe au memoire et linstancier
            Class cDao=Class.forName(daoClassName);
            Class cMetier=Class.forName(metierClassName);

            IDao dao= (IDao) cDao.newInstance();
            IMetier metier= (IMetier) cMetier.newInstance();

            //appeler dynamiquement une methode (setDao(Idao) )
            Method m=cMetier.getMethod("setDao",new Class[]{IDao.class});
            m.invoke(metier, new Object[]{dao});//equivalent a dao.setDao


            System.out.println(metier.calcul());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
