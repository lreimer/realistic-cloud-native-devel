FROM qaware/distroless-zulu-payara-micro:8u242-5.201
COPY build/libs/realistic-cloud-native-devel.war $DEPLOY_DIR

# RUN java -jar /opt/payara/payara-micro.jar --rootdir /opt/payara/root --warmup $DEPLOY_DIR/realistic-cloud-native-devel.war
# RUN java -jar /opt/payara/payara-micro.jar --rootdir /opt/payara/root --outputlauncher
# ENTRYPOINT ["java", "-server", "-Xshare:on", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=50.0", "-XX:ThreadStackSize=256", "-XX:MaxMetaspaceSize=128m", "-XX:+UseG1GC", "-XX:MaxGCPauseMillis=500", "-XX:+UseStringDeduplication", "-jar", "/opt/payara/root/launch-micro.jar"]
# CMD []
