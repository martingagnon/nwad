config.module.rules.push({
    test: /\.(png|jpe?g|gif)$/i,
    loader: 'file-loader',
    options: {
      name: '/images/[name].[ext]',
    }
});

config.module.rules.push({
    test: /\.svg$/,
    loader: '@svgr/webpack'
});
