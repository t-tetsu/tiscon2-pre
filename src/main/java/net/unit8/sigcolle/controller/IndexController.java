package net.unit8.sigcolle.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.dao.CampaignDao;
import net.unit8.sigcolle.model.Campaign;

public class IndexController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider domaProvider;

    public HttpResponse index() {
        List<Campaign> campaigns = new ArrayList<Campaign>();
        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        campaigns = campaignDao.selectAll();

        return templateEngine.render("index",
                "campaigns", campaigns);
    }
}
