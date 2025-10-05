package repository;

import dto.DTOReporte;
import entities.Inscripcion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

public class RepositoryInscripcionIMP implements RepositoryInscripcion {
    private EntityManagerFactory emf;
    private EntityManager em;
    private static RepositoryInscripcionIMP instance;

    private RepositoryInscripcionIMP(EntityManagerFactory emf){
        this.emf = emf;
    }

    public static RepositoryInscripcionIMP getInstance(EntityManagerFactory emf) {
        if (instance == null) {
            instance = new RepositoryInscripcionIMP(emf);
        }
        return instance;
    }

    @Override
    public void save(Inscripcion i) {
        em = emf.createEntityManager();
        if (!em.contains(i)) {
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
        } else {
            em.merge(i);
        }
        em.close();

        // Verificación posterior al guardado
        em = emf.createEntityManager();
        Inscripcion savedInscripcion = em.find(Inscripcion.class, i.getIdInscripcion());
//        if (savedInscripcion != null) {
//            System.out.println("La inscripción se guardó correctamente con ID: " + savedInscripcion.getIdInscripcion());
//        } else {
//            System.out.println("Error al guardar la inscripción.");
//        }
        em.close();
    }

    @Override
    public List<DTOReporte> crearInforme() {
        em = emf.createEntityManager();

        String query =
                "SELECT fj.nombreCarrera, fj.anio, SUM(fj.inscriptos), SUM(fj.graduado)\r\n"
                        + "FROM\r\n"
                        + "    (\r\n"
                        + "        SELECT c.nombreCarrera, EXTRACT(YEAR FROM i.fechaInscripcion) as anio, 0 as inscriptos, COUNT(*) as graduado\r\n"
                        + "        FROM Inscripcion i JOIN Carrera c ON c.idCarrera = i.carrera_idCarrera\r\n"
                        + "        GROUP BY c.nombreCarrera, anio\r\n"
                        + "        UNION\r\n"
                        + "        SELECT c.nombreCarrera, YEAR(i.fechaEgreso) AS anio, COUNT(*) AS inscriptos, 0 as graduado\r\n"
                        + "        FROM Inscripcion i JOIN Carrera c ON c.idCarrera = i.carrera_idCarrera\r\n"
                        + "        GROUP BY c.nombreCarrera, anio HAVING anio IS NOT NULL\r\n"
                        + "    ) as fj\r\n"
                        + "GROUP BY fj.nombreCarrera, fj.anio\r\n"
                        + "ORDER BY fj.nombreCarrera, fj.anio";

        List<Object[]> queryList = em.createNativeQuery(query).getResultList();
        List<DTOReporte> report = new ArrayList<>();

        for (Object[] queryListRow : queryList) {
            DTOReporte r = new DTOReporte(
                    (String) queryListRow[0],  // nombreCarrera
                    ((Integer) queryListRow[1]), // anio
                    ((Integer) queryListRow[2]), // inscriptos
                    ((Integer) queryListRow[3])  // graduados
            );
            report.add(r);
        }

        em.close();
        return report;
    }
}
