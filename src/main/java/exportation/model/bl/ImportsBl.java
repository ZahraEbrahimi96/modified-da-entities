package exportation.model.bl;

import exportation.model.entity.Country;
import lombok.Getter;
import exportation.controller.exceptions.NoImportsFoundException;
import exportation.model.da.ImportsDa;
import exportation.model.entity.Imports;
import exportation.model.tools.CRUD;

import java.util.List;

public class ImportsBl implements CRUD<Imports> {
    @Getter
    private static ImportsBl importsBl = new ImportsBl();

    private ImportsBl() {
    }

    //save
    @Override
    public Imports save(Imports imports) throws Exception {
        try (ImportsDa importsDa = new ImportsDa()) {
            importsDa.save(imports);
            return imports;
        }
    }

    //edit
    @Override
    public Imports edit(Imports imports) throws Exception {
        try (ImportsDa importsDa = new ImportsDa()) {
            if (importsDa.findById(imports.getId()) != null) {
                importsDa.edit(imports);
                return imports;
            } else {
                throw new NoImportsFoundException();
            }
        }
    }

    //remove
    @Override
    public Imports remove(int id) throws Exception {
        try (ImportsDa importsDa = new ImportsDa()) {
            Imports imports = importsDa.findById(id);
            if (imports != null) {
                importsDa.remove(id);
                return imports;
            } else {
                throw new NoImportsFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Imports> findAll() throws Exception {
        try (ImportsDa importsDa = new ImportsDa()) {
            List<Imports> perosnList = importsDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoImportsFoundException();
            }
        }
    }

    //findById
    @Override
    public Imports findById(int id) throws Exception {
        try (ImportsDa importsDa = new ImportsDa()) {
            Imports imports = importsDa.findById(id);
            if (imports != null) {
                return imports;
            } else {
                throw new NoImportsFoundException();
            }
        }
    }


    //findByCountry
    public List<Imports> findByCountry(Country country) throws Exception {
        try (ImportsDa importsDa = new ImportsDa()) {
            List<Imports> perosnList = importsDa.findByCountry(country);
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoImportsFoundException();
            }
        }
    }
}
