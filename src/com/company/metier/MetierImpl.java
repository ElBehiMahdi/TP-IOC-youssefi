package com.company.metier;

import com.company.dao.IDao;

public class MetierImpl implements IMetier{
    private IDao dao;
    @Override
    public double calcul() {
        double t=dao.getTemp();
        return t*7;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }


}
