module ExamenHibernateSInJavaFX {
    requires jakarta.persistence;
    requires java.xml.crypto;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens org.example.opinion to org.hibernate.orm.core;
    opens org.example.pelicula to org.hibernate.orm.core;


    opens org.example to org.hibernate.orm.core;


    exports org.example;
    exports org.example.opinion;
    exports org.example.pelicula;
}
