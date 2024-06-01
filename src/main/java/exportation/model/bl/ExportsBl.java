package exportation.model.bl;

import lombok.Getter;
import exportation.controller.exceptions.NoExportsFoundException;
import exportation.model.da.ExportsDa;
import exportation.model.entity.Exports;
import exportation.model.tools.CRUD;

import java.util.List;

public class ExportsBl implements CRUD<Exports> {
    @Getter
    private static ExportsBl exportBl = new ExportsBl();

    private ExportsBl() {
    }

    //save
    @Override
    public Exports save(Exports export) throws Exception {
        try (ExportsDa exportDa = new ExportsDa()) {
            exportDa.save(export);
            return export;
        }
    }

    //edit
    @Override
    public Exports edit(Exports export) throws Exception {
        try (ExportsDa exportDa = new ExportsDa()) {
            if (exportDa.findById(export.getId()) != null) {
                exportDa.edit(export);
                return export;
            } else {
                throw new NoExportsFoundException();
            }
        }
    }

    //remove
    @Override
    public Exports remove(int id) throws Exception {
        try (ExportsDa exportDa = new ExportsDa()) {
            Exports export = exportDa.findById(id);
            if (export != null) {
                exportDa.remove(id);
                return export;
            } else {
                throw new NoExportsFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Exports> findAll() throws Exception {
        try (ExportsDa exportDa = new ExportsDa()) {
            List<Exports> perosnList = exportDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoExportsFoundException();
            }
        }
    }

    //findById
    @Override
    public Exports findById(int id) throws Exception {
        try (ExportsDa exportDa = new ExportsDa()) {
            Exports export = exportDa.findById(id);
            if (export != null) {
                return export;
            } else {
                throw new NoExportsFoundException();
            }
        }
    }
}
