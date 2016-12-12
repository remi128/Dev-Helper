#!/bin/sh

function error_exit
{
	echo "$1" 1>&2
	exit 1
}

# $1 = command install oder deploy
# $2 = project path
function execute
{
	echo "STARTING mvn clean" $1 on $2
	if cd $2; then
	        pwd
			if mvn clean $1;then
			  echo "FINSHED TASK " $2
			else
				error_exit "Maven error!  Aborting."
			fi
	else
		error_exit "Cannot change directory!  Aborting."
	fi
}


if [ "$1" != "" ]; then
    echo "Starting maven cycle with " $1
else
		error_exit "Maven command missing: install / deploy"
fi

# execute $1 "../vertx-auth-datastore/"
execute $1  "../NetRelay/"
execute $1  "../NetRelay-Controller/"
execute $1 "../NetRelay-PdfController/"
execute $1 "../NetRelay-DemoProject/"

