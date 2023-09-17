package picpaydesafiobackend;

/*import com.example.picpaydesafiobackend.common.repository.JpaConfig;*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;


@SpringBootApplication
/*@Import(JpaConfig.class)
@EntityScan(basePackages = "com.example.picpaydesafiobackend.authentication.entity")*/
public class PicpayDesafioBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PicpayDesafioBackendApplication.class, args);
    }

}
