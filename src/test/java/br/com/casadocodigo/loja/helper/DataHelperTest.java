package br.com.casadocodigo.loja.helper;

import org.junit.Assert;
import org.junit.Test;

public class DataHelperTest {

    @Test
    public void deveRetornaDataValida() {

        String dataValida = "2018-10-12";

        DataHelper dataHelper = new DataHelper(dataValida);

        Assert.assertTrue(dataHelper.isDataValida());
    }

    @Test
    public void deveRetornaDataInvalida() {

        String dataInValida = "2018-15-12";

        DataHelper dataHelper = new DataHelper(dataInValida);

        Assert.assertFalse(dataHelper.isDataValida());
    }
}