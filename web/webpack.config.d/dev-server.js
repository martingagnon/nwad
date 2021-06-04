config.devServer = config.devServer || {};
config.devServer.port = process.env.CANONICAL_PORT || 3000;

// Prevent dev-server to automatically open the browser after server has been started
// See https://webpack.js.org/configuration/dev-server/#devserveropen
config.devServer.open = false;

// Use HTML5 History API instead of serving 404 for known routes
// See https://webpack.js.org/configuration/dev-server/#devserverhistoryapifallback
config.devServer.historyApiFallback = true;

// Required for Live Reloading to work when navigating in SSL/TLS
// See https://webpack.js.org/configuration/dev-server/#devserversockhost
if (process.env.USE_SSL == 'true') {
    config.devServer.sockHost = process.env.CANONICAL_HOST || null;
    config.devServer.sockPort = 443;
}
