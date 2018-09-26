var path = require('path');
var webpack = require('webpack');

const DEBUG = true;

module.exports = {
    devtool: 'source-map',
    entry: './src/main/webapp/js/index.js',
    output: {
        path: path.join(__dirname, 'src/main/webapp/build'),
        filename: 'app-bundle.js'
    },
    resolve: {extensions: ['.js', '.jsx']},
    plugins: [
        new webpack.LoaderOptionsPlugin({debug: true}),
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify("development")
            }
        })
    ],
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                loader: 'babel-loader',
                include: [
                    path.resolve(__dirname, './src/main/webapp/js'),
                ],
                query: {
                    // https://github.com/babel/babel-loader#options
                    cacheDirectory: DEBUG,

                    // https://babeljs.io/docs/usage/options/
                    babelrc: false,
                    presets: [
                        'react',
                        'es2015',
                        'stage-0',
                    ],
                    plugins: [
                        'transform-runtime',
                        ...DEBUG ? [] : [
                            'transform-react-remove-prop-types',
                            'transform-react-constant-elements',
                            'transform-react-inline-elements',
                        ],
                    ],
                },
            },
            {
                test: /\.css$/,
                use: [
                    'style-loader',
                    'css-loader'
                ]
            },
            {
                test: /\.scss$/,
                loaders: [
                    'isomorphic-style-loader',
                    `css-loader?${JSON.stringify({ sourceMap: DEBUG, minimize: !DEBUG })}`,
                    'postcss-loader?pack=sass',
                    'sass-loader',
                ],
            },
            {
                test: /\.json$/,
                loader: 'json-loader',
            },
            {
                test: /\.txt$/,
                loader: 'raw-loader',
            },
            {
                test: /\.(png|jpg|jpeg|gif|svg|woff|woff2)$/,
                loader: 'url-loader',
                query: {
                    name: DEBUG ? '[path][name].[ext]?[hash]' : '[hash].[ext]',
                    limit: 10000,
                },
            },
            {
                test: /\.(eot|ttf|wav|mp3)$/,
                loader: 'file-loader',
                query: {
                    name: DEBUG ? '[path][name].[ext]?[hash]' : '[hash].[ext]',
                },
            },
        ],

    },
    devServer: {
        noInfo: false,
        quiet: false,
        lazy: false,
        watchOptions: {
            poll: true
        }
    }
}