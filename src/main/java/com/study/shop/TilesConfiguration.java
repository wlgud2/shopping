package com.study.shop;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
 
@Configuration
public class TilesConfiguration {
  @Bean
  public TilesConfigurer tilesConfigurer() {
      final TilesConfigurer configurer = new TilesConfigurer(); // 레이아웃(탑, 바텀, 양옆), 제목, 브라우저 탭에 뜨는 제목
      //해당 경로에 tiles.xml 파일을 넣음
      configurer.setDefinitions(new String[]{"classpath:/tiles/tiles_member.xml",
    		  "classpath:/tiles/tiles_contents.xml", "classpath:/tiles/tiles_notice.xml"});
      configurer.setCheckRefresh(true);
      return configurer;
  }
 
  @Bean
  public TilesViewResolver tilesViewResolver() {//tiles config안에 선언된 xml안에있는 definition이름을 찾아가게 만드는 것
      final TilesViewResolver tilesViewResolver = new TilesViewResolver();//jsp 찾아오는 과정(prefix, serfix)처럼이 아닌, 리턴되는 뷰를 tiles.xml에 있는 이름 중에서 찾는다
      tilesViewResolver.setViewClass(TilesView.class);
      return tilesViewResolver;
  }
}
