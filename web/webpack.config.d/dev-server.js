config.devServer = config.devServer || {};
config.devServer.port = process.env.CANONICAL_PORT || 3000;

// Prevent dev-server to automatically open the browser after server has been started
// See https://webpack.js.org/configuration/dev-server/#devserveropen
config.devServer.open = false;

// Ease the usage of jasper.local instead of localhost
// See https://webpack.js.org/configuration/dev-server/#devserverdisablehostcheck
config.devServer.disableHostCheck = true;

// Required for Live Reloading to work when navigating in SSL/TLS
// See https://webpack.js.org/configuration/dev-server/#devserversockhost
if (process.env.USE_SSL == 'true') {
    config.devServer.sockHost = process.env.CANONICAL_HOST || null;
    config.devServer.sockPort = 443;
}
