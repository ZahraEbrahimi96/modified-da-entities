package exportation.model.bl;
import exportation.model.da.ManufacturerDa;
import lombok.Getter;
import exportation.controller.exceptions.NoManufacturerFoundException;
import exportation.model.entity.Manufacturer;
import exportation.model.tools.CRUD;
import java.util.List;

public class ManufacturerBl implements CRUD<Manufacturer> {
    @Getter
    private static ManufacturerBl manufacturerBl = new ManufacturerBl();

    private ManufacturerBl() {
    }

    //save
    @Override
    public Manufacturer save(Manufacturer manufacturer) throws Exception {
        try (ManufacturerDa manufacturerDa = new ManufacturerDa()) {
            manufacturerDa.save(manufacturer);
            return manufacturer;
        }
    }

    //edit
    @Override
    public Manufacturer edit(Manufacturer manufacturer) throws Exception {
        try (ManufacturerDa manufacturerDa = new ManufacturerDa()) {
            if (manufacturerDa.findById(manufacturer.getId()) != null) {
                manufacturerDa.edit(manufacturer);
                return manufacturer;
            } else {
                throw new NoManufacturerFoundException();
            }
        }
    }

    //remove
    @Override
    public Manufacturer remove(int id) throws Exception {
        try (ManufacturerDa manufacturerDa = new ManufacturerDa()) {
            Manufacturer manufacturer = manufacturerDa.findById(id);
            if (manufacturer != null) {
                manufacturerDa.remove(id);
                return manufacturer;
            } else {
                throw new NoManufacturerFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Manufacturer> findAll() throws Exception {
        try (ManufacturerDa manufacturerDa = new ManufacturerDa()) {
            List<Manufacturer> perosnList = manufacturerDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoManufacturerFoundException();
            }
        }
    }

    //findById
    @Override
    public Manufacturer findById(int id) throws Exception {
        try (ManufacturerDa manufacturerDa = new ManufacturerDa()) {
            Manufacturer manufacturer = ManufacturerDa.findById(id);
            if (manufacturer != null) {
                return manufacturer;
            } else {
                throw new NoManufacturerFoundException();
            }
        }
    }


    //findByName
    public List<Manufacturer> findByName(String name) throws Exception {
        try (ManufacturerDa manufacturerDa = new ManufacturerDa()) {
            List<Manufacturer> manufacturerList = manufacturerDa.findByName(name);
            if (!manufacturerList.isEmpty()) {
            } else {
                throw new NoManufacturerFoundException();
            }
        }
    }
}