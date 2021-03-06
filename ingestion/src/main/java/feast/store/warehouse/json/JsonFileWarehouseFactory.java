/*
 * Copyright 2019 The Feast Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package feast.store.warehouse.json;

import com.google.auto.service.AutoService;
import feast.ingestion.model.Specs;
import feast.store.FeatureStoreWrite;
import feast.options.OptionsParser;
import feast.specs.StorageSpecProto.StorageSpec;
import feast.store.FileStoreOptions;
import feast.store.warehouse.FeatureWarehouseFactory;
import lombok.AllArgsConstructor;

@AutoService(FeatureWarehouseFactory.class)
@AllArgsConstructor
public class JsonFileWarehouseFactory implements FeatureWarehouseFactory {

  private static final String JSON_FILES_TYPE = "file.json";

  @Override
  public FeatureStoreWrite create(StorageSpec storageSpec, Specs specs) {
    FileStoreOptions options =
        OptionsParser.parse(storageSpec.getOptionsMap(), FileStoreOptions.class);
    options.jobName = specs.getJobName();
    return new JsonFileWarehouseWrite(options);
  }

  @Override
  public String getType() {
    return JSON_FILES_TYPE;
  }
}
