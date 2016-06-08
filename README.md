# Dev-Helper
dev tools and reactor build


Release - HowTo

Main_POMCreator_pojo_mapper
( führt Release für pojo-mapper und Projekte darunter aus )
- Setze BTBASE_VERSION auf nicht SNAPSHOT ( z.B. "15" )
- Setze BT_VERTX_VERSION auf nicht SNAPSHOT ( z.B. 1.1.0 )
- Ausführen von Main_POMCreator_pojo_mapper erzeugt neue poms für alle Projekte
- Ausführen von sh installPojoMapper -deploy
- alle Projekte committen und pushen, wenn nötig
- TaggingPojoMapper ausführen ( erzeugt Tags für alle Projekte mit Main_POMCreator_pojo_mapper.BT_VERTX_VERSION )
==>> Bei Bedarf den gleichen Prozess ausführen mit Main_POMCreator_NetRelay und TaggingNetRelay
- Setze BTBASE_VERSION auf nächst höheren SNAPSHOT ( z.B. "16-SNAPSHOT" )
- Setze BT_VERTX_VERSION auf nächst höheren SNAPSHOT ( z.B. 1.2.0-SNAPSHOT )
- Ausführen von Main_POMCreator_pojo_mapper erzeugt neue poms für alle Projekte
- Ausführen von Main_POMCreator_NetRelay, damit auch die das neue SNAPSHOT übernehmen

PRÜFEN VON Maven Release Plugin 

