
# Eudex phonetic plugin for Elasticsearch

Eudex is a Soundex-esque phonetic reduction/hashing algorithm, providing locality sensitive "hashes" of words, based on the spelling and pronunciation.

This is a Java port of [Eudex](https://github.com/ticki/eudex), which is implemented in Rust.

## Compatibility matrix

| Plugin version   | Elasticsearch version | Release date |
| -----------------| ----------------------| -------------|
| 2.3.2.0          | 2.3.2                 | May  8 2016  |

## Installation

### Elasticsearch 2.x

    ./bin/plugin install http://xbib.org/repository/org/xbib/elasticsearch/plugin/elasticsearch-analysis-phonetic-eudex/2.3.2.0/elasticsearch-analysis-phonetic-eudex-2.3.2.0-plugin.zip

Do not forget to restart the node after installing.

## Issues

All feedback is welcome! If you find issues, please post them at [Github](https://github.com/jprante/elasticsearch-analysis-phonetic-eudex/issues)

# Example

In the mapping, use an analyzer or token filter of type "eudex"::

    {
        "index":{
            "analysis":{
                "analyzer":{
                    "my_phonetic":{
                        "type":"eudex"
                    }
                },
                "filter":{
                    "my_phonetic":{
                        "type":"eudex"
                    }
                },
                "tokenizer":{
                    "my_phonetic":{
                        "type": "standard",
                        "filter":[ "my_phonetic" ]
                    }
                }
            }
        }
    }


# License

Eudex Phonetic Analysis Plugin for Elasticsearch

Copyright (C) 2016 Jörg Prante

Derived work of Eudex https://github.com/ticki/eudex

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.