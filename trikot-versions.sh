#!/bin/bash
BASE_PATH=https://mirego-maven.s3.amazonaws.com/public/com/mirego/trikot
XPATH="metadata/versioning/latest/text()"
FOUNDATION=$((curl --silent $BASE_PATH/trikotFoundation/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
STREAMS=$((curl --silent $BASE_PATH/streams/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
HTTP=$((curl --silent $BASE_PATH/http/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
DATASOURCE=$((curl --silent $BASE_PATH/datasources/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
GRAPHQL=$((curl --silent $BASE_PATH/graphql/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
KWORD=$((curl --silent $BASE_PATH/kword/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
VIEW_MODEL_DECLARATIVE=$((curl --silent $BASE_PATH/viewmodels-declarative/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)
VIEW_MODEL=$((curl --silent $BASE_PATH/viewmodels/maven-metadata.xml | xmllint --xpath $XPATH -)  2>&1)

echo "trikot_foundation_version=$FOUNDATION"
echo "trikot_streams_version=$STREAMS"
echo "trikot_http_version=$HTTP"
echo "trikot_datasources_version=$DATASOURCE"
echo "trikot_graphql_version=$GRAPHQL"
echo "trikot_kword_version=$KWORD"
echo "trikot_viewmodels_version=$VIEW_MODEL"
echo "trikot_viewmodels_declarative_version=$VIEW_MODEL_DECLARATIVE"
