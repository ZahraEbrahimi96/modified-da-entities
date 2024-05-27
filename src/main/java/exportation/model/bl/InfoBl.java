package exportation.model.bl;

import lombok.Getter;
import exportation.controller.exceptions.NoInfoFoundException;
import exportation.model.da.InfoDa;
import exportation.model.entity.Info;
import exportation.model.tools.CRUD;

import java.util.List;

public class InfoBl implements CRUD<Info> {
    @Getter
    private static InfoBl infoBl = new InfoBl();

    private InfoBl() {
    }

    //save
    @Override
    public Info save(Info info) throws Exception {
        try (InfoDa infoDa = new InfoDa()) {
            infoDa.save(info);
            return info;
        }
    }

    //edit
    @Override
    public Info edit(Info info) throws Exception {
        try (InfoDa infoDa = new InfoDa()) {
            if (infoDa.findById(info.getId()) != null) {
                infoDa.edit(info);
                return info;
            } else {
                throw new NoInfoFoundException();
            }
        }
    }

    //remove
    @Override
    public Info remove(int id) throws Exception {
        try (InfoDa infoDa = new InfoDa()) {
            Info info = infoDa.findById(id);
            if (info != null) {
                infoDa.remove(id);
                return info;
            } else {
                throw new NoInfoFoundException();
            }
        }
    }

    //findAll
    @Override
    public List<Info> findAll() throws Exception {
        try (InfoDa infoDa = new InfoDa()) {
            List<Info> perosnList = infoDa.findAll();
            if (!perosnList.isEmpty()) {
                return perosnList;
            } else {
                throw new NoInfoFoundException();
            }
        }
    }

    //findById
    @Override
    public Info findById(int id) throws Exception {
        try (InfoDa infoDa = new InfoDa()) {
            Info info = infoDa.findById(id);
            if (info != null) {
                return info;
            } else {
                throw new NoInfoFoundException();
            }
        }
    }

}
