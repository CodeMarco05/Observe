package com.observe.os1.systemDataControllers;

import com.observe.os1.models.SystemInfoModel;
import com.observe.os1.models.TmpClientModel;
import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class SystemInfoController {

    @Transactional
    void onStartup(@Observes StartupEvent event) {

        //if it is the first startup, generate an initial password and log it
        if (SystemInfoModel.count() == 0) {
            TmpClientModel tmpClientModel = new TmpClientModel();
            tmpClientModel.persistAndFlush();
            Log.info("Temporary password for first client: " + tmpClientModel.getTemporaryPassword());
        } else {
            new TmpClientModel().persistAndFlush();
            Log.info("SystemInfo already exists, no new entry created.");
        }
    }
}
