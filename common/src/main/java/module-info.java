module common {
    requires lombok;
    requires org.tinylog.api;
    exports pl.horazon.barrel.common.pojo;
    exports pl.horazon.barrel.common.thread;
    exports pl.horazon.barrel.common.map;
    exports pl.horazon.barrel.common.pojo.domain;
    exports pl.horazon.barrel.common.pojo.enums;
    exports pl.horazon.barrel.common.pojo.system;
}