package exportation.model.bl;

import lombok.Getter;
import exportation.controller.exceptions.NoAccessPathFoundException;
import exportation.model.da.AccessPathDa;
import exportation.model.entity.AccessPath;
import exportation.model.tools.CRUD;

import java.util.List;

public class AccessPathBl implements CRUD<AccessPath> {
    @Getter
    private static AccessPathBl accessPathBl = new AccessPathBl();

    private AccessPathBl() {
    }

    //save
    @Override
    public AccessPath save(AccessPath accessPath) throws Exception {
        try (AccessPathDa accessPathDa = new AccessPathDa()) {
            accessPathDa.save(accessPath);
            return accessPath;
        }
    }

    //edit
    @Override
    public AccessPath edit(AccessPath accessPath) throws Exception {
        try (AccessPathDa accessPathDa = new AccessPathDa()) {
            if (accessPathDa.findById(accessPath.getId()) != null) {
                accessPathDa.edit(accessPath);
                return accessPath;
            } else {
                throw new NoAccessPathFoundException();
            }
        }
    }

    //remove
    @Override
    public AccessPath remove(int id) throws Exception {
        try (AccessPathDa accessPathDa = new AccessPathDa()) {
            AccessPath accessPath = accessPathDa.findById(id);
            if (accessPath != null) {
                accessPathDa.remove(id);
                return accessPath;
            } else {
                throw new NoAccessPathFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<AccessPath> findAll() throws Exception {
        try (AccessPathDa accessPathDa = new AccessPathDa()) {
            List<AccessPath> perosnList = accessPathDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoAccessPathFoundException();
            }
        }
    }

    //findById
    @Override
    public AccessPath findById(int id) throws Exception {
        try (AccessPathDa accessPathDa = new AccessPathDa()) {
            AccessPath accessPath = accessPathDa.findById(id);
            if (accessPath != null) {
                return accessPath;
            } else {
                throw new NoAccessPathFoundException();
            }
        }
    }
}
