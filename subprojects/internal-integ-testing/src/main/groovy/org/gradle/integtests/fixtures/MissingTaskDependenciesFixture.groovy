/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.fixtures

import groovy.transform.SelfType

@SelfType(AbstractIntegrationSpec)
trait MissingTaskDependenciesFixture {
    void expectMissingDependencyDeprecation(String producer, String consumer, File producedConsumedLocation) {
        executer.expectDocumentedDeprecationWarning(
            "Gradle detected a problem with the following location: '${producedConsumedLocation.absolutePath}'. " +
                "Task '${consumer}' uses this output of task '${producer}' without declaring an explicit dependency (using Task.dependsOn() or Task.mustRunAfter()) or an implicit dependency (declaring task '${producer}' as an input). " +
                "This can lead to incorrect results being produced, depending on what order the tasks are executed. " +
                "This behaviour has been deprecated and is scheduled to be removed in Gradle 7.0. " +
                "Execution optimizations are disabled due to the failed validation. " +
                "See https://docs.gradle.org/current/userguide/more_about_tasks.html#sec:up_to_date_checks for more details.")
    }
}
