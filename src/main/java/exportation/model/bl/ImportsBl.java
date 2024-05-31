package exportation.model.bl;

import exportation.controller.exceptions.NoExportFoundException;
import exportation.controller.exceptions.NoImportFoundException;
import exportation.model.da.ExportsDa;
import exportation.model.da.ImportsDa;
import exportation.model.entity.Country;
import exportation.model.entity.Exports;
import exportation.model.entity.Imports;
import jdk.nashorn.internal.objects.annotations.Getter;

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
            if (importsDa.findById(imports.getId) != null) {
                importsDa.edit(imports);
                return imports;
            } else {
                throw new NoImportFoundException();
            }
        }
    }



    //remove
    @Override
    public Imports remove(int id) throws Exception{
        try(ImportsDa importsDa= new ImportsDa()) {
            Imports imports = importsDa.findById(id);
            if (imports != null) {
                importsDa.remove(id);
                return  imports;
            } else {
                throw new NoImportFoundException();
            }
        }
    }



    //findAll
    @Override
    public List<Imports> findAll() throws Exception{
        try (ImportsDa importsDa=new ImportsDa()) {
            List<Imports> importsList= importsDa.findAll();
            if(!importsList.isEmpty()) {
                return importsList;
            }else {
                throw new NoImportFoundException();
            }
        }
    }



    //findById
    @Override
    public Imports findById(int id) throws Exception{
        try(ImportsDa importsDa= new ImportsDa()) {
            Imports imports= importsDa.findById(id);
            if(imports != null) {
                return imports;
            }else {
                throw new NoImportFoundException();
            }
        }
    }



    //findByCountry
    public List<Imports> findByCountry(Country country) throws Exception{
        try(ImportsDa importsDa= new ImportsDa()) {
            List<Imports> importsList= importsDa.findByCountry(country);
            if(!importsList.isEmpty()) {
                return importsList;
            }else {
                throw new NoImportFoundException();
            }
        }
    }
}