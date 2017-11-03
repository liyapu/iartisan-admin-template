package org.iartisan.admin.template.sitemesh;

import org.iartisan.runtime.env.EnvContextConfig;
import org.iartisan.runtime.utils.StringUtils;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class WebSiteMeshConfig extends ConfigurableSiteMeshFilter {

    private List<String> excluedPaths = new ArrayList<>();

    private static final String _CONFIG = "iartisan.sitemesh.excluedpath";

    public void setExcluedPaths() {
        String excluedPath = EnvContextConfig.get(_CONFIG);
        if (StringUtils.isNotEmpty(excluedPath)) {
            excluedPaths = Arrays.asList(excluedPath.split(","));
        }
    }

    public WebSiteMeshConfig() {
        setExcluedPaths();
    }

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addTagRuleBundles(new ExtHtmlRuleBundle());
        //include 请求
        /*builder.addDecoratorPath("/*", "/decorator");
        //exclude 请求
        for (String excluedPath : excluedPaths) {
            builder.addExcludedPath(excluedPath);
        }*/
    }
}
