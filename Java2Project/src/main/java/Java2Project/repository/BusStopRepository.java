package Java2Project.repository;

import Java2Project.domain.BusStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusStopRepository extends JpaRepository<BusStop,String> {
    //버스 정류장 이름으로 BusStop 객체 불러옴
    List<BusStop> findByStopName(String stopName);

    //위도 경도로 BusStop 객체 불러옴
    //반경 8m 정류장을 가져옴
    @Query("SELECT " +
            "M " +
            "FROM BusStop M " +
            "WHERE M.latitude BETWEEN :latitude-0.0008 AND :latitude+0.0008" +
            "AND M.longitude BETWEEN  :longitude-0.0008 AND :longitude+0.0008"
    )
    List<BusStop> findByLatitudeAndLongitudeWithOption(@Param("latitude") double latitude, @Param("longitude") double longitude);
}