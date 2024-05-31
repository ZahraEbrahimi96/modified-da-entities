package exportation.model.bl;
import exportation.controller.exceptions.NoExportFoundException;
import exportation.model.entity.Country;
import exportation.model.entity.Exports;
import exportation.model.da.ExportsDa;
import exportation.model.tools.CRUD;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.List;

public class ExportsBl implements CRUD<Exports> {
    @Getter
    private static ExportsBl exportsBl = new ExportsBl();

    private ExportsBl() {
    }

    //save
    @Override
    public Exports save(Exports exports) throws Exception {
        try (ExportsDa exportsDa = new ExportsDa()) {
            exportsDa.save(exports);
            return exports;
        }
    }

    //edit
    @Override
    public Exports edit(Exports exports) throws Exception {
        try (ExportsDa exportsDa = new ExportsDa()) {
            if (exportsDa.findById(exports.getId) != null) {
                exportsDa.edit(exports);
                return exports;
            } else {
                throw new NoExportFoundException();
            }
        }
    }

    //remove
    @Override
    public Exports remove(int id) throws Exception {
        try (ExportsDa exportsDa = new ExportsDa()) {
            Exports exports = exportsDa.findById(id);
            if (exports != null) {
                exportsDa.remove(id);
                return exports;
            } else {
                throw new NoExportFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Exports> findAll() throws Exception {
        try (ExportsDa exportsDa = new ExportsDa()) {
            List<Exports> exportsList = exportsDa.findAll();
            if (!exportsList.isEmpty()) {
                return exportsList;
            } else {
                throw new NoExportFoundException();
            }
        }
    }


    //findById
    @Override
    public Exports findById(int id) throws Exception {
        try (ExportsDa exportsDa = new ExportsDa()) {
            Exports exports = exportsDa.findById(id);
            if (exports != null) {
                return exports;
            } else {
                throw new NoExportFoundException();
            }
        }
    }


    //findByCountry
    public List<Exports> findByCountry(Country country) throws Exception {
        try (ExportsDa exportsDa = new ExportsDa()) {
            List<Exports> exportsList = exportsDa.findByCountry(country);
            if (!exportsList.isEmpty()) {
                return exportsList;
            } else {
                throw new NoExportFoundException();
            }
        }
    }
}
