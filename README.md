# clj-nvd

A sketch of using [lein-nvd](https://github.com/rm-hull/lein-nvd) with deps.edn.

## Usage

```sh
clojure -Sdeps '{:deps {clj-nvd {:git/url "https://github.com/miikka/clj-nvd.git" :sha "141d54f5304e53f6caa6e3de1677f3cfb04091f4"}}}' -m clj-nvd.core check
```

Alternatively, add clj-nvd as a git dependency to your `deps.edn`:

```clojure
{:aliases {:clj-nvd {:extra-deps {clj-nvd {:git/url "https://github.com/miikka/clj-nvd.git"
                                           :sha "141d54f5304e53f6caa6e3de1677f3cfb04091f4"}}
                     :main-opts ["-m" "clj-nvd.core"]}}}
```

```sh
clojure -A:clj-nvd check
```

To specify the `:extra-deps` aliases to check, use `-A`:

```sh
clojure -A:clj-nvd check -A backend:frontend
```
