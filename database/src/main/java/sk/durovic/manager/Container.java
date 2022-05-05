package sk.durovic.manager;

import sk.durovic.model.BaseEntityAbstractClass;

import java.util.List;
import java.util.Map;

class Container {


    static class Node<ID, T extends BaseEntityAbstractClass<ID>, E extends Version>{

        ID id;
        T entity;
        E version;



    }
}
