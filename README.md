# clj-nvd

Check your deps.edn dependencies against known security vulnerabilities in [National Vulnerability Database][nvd]. In other words, like [lein-nvd][lein-nvd] but for deps.edn.

*clj-nvd uses lein-nvd as a library. To learn how the checking works, check out [lein-nvd's README][lein-nvd].*

[nvd]: https://nvd.nist.gov

### Installation

*clj-nvd has not been released yet, so you'll have to use a git dependency.*

```sh
clojure -Sdeps '{:deps {clj-nvd {:git/url "https://github.com/miikka/clj-nvd.git" :sha "141d54f5304e53f6caa6e3de1677f3cfb04091f4"}}}' -m clj-nvd.core check
```

Alternatively, add clj-nvd as a git dependency to your `deps.edn`:

```clojure
{:aliases
 {:clj-nvd {:extra-deps {clj-nvd {:git/url "https://github.com/miikka/clj-nvd.git"
                                  :sha "141d54f5304e53f6caa6e3de1677f3cfb04091f4"}}
            :main-opts ["-m" "clj-nvd.core"]}}}
```

```sh
clojure -A:clj-nvd check
```

## Usage

clj-nvd supports [the same commands as lein-nvd](https://github.com/rm-hull/lein-nvd#other-commands): `check`, `update`, and `purge`. To specify the `:extra-deps` aliases to check, use `-A`:

```sh
clojure -A:clj-nvd check -A backend:frontend
```

More detailed reports are created in the directory `target/nvd`.

## Configuration

The configuration is loaded from the file `clj-nvd.edn`. See [configuration options](https://github.com/rm-hull/lein-nvd#configuration-options) in lein-nvd's documentation.

## Attribution

clj-nvd is just small wrapper on [lein-nvd][lein-nvd] by Richard Hull, which in turns relies on [DependencyCheck][depcheck] Jeremy Long. To understand how to use [tools.deps.alpha][tools.deps], I looked at the source code of [Pack][pack] and [Depot][depot].

[lein-nvd]: https://github.com/rm-hull/lein-nvd
[pack]: https://github.com/juxt/pack.alpha
[depot]: https://github.com/Olical/depot
[tools.deps]: https://github.com/clojure/tools.deps.alpha
[depcheck]: https://github.com/jeremylong/DependencyCheck

## License

Copyright © 2019 Metosin Oy.

Distributed under the Eclipse Public License 2.0.
