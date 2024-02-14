package com.example.newcomertoolbox.router;

import com.example.newcomertoolbox.view.bill.BillView;
import com.example.newcomertoolbox.view.indexview.IndexView;
import com.example.newcomertoolbox.view.jdkmanage.JdkManage;
import com.example.newcomertoolbox.view.sqltoword.SqltoWordView;
import com.example.newcomertoolbox.view.template.TemplateView;

import java.util.Map;

public class RouterInstance {
    private static Router router;

    public static Router getInstance() {
        if (router == null) {
            router = new Router(
                    Map.of(
                          "/", IndexView.build(),
                            "/sqltoword", SqltoWordView.build(),
                            "/jdkmanage", JdkManage.build(),
                            "/template", TemplateView.build(),
                            "/bill", BillView.build()
                    )
            );
        }
        return router;
    }
}
