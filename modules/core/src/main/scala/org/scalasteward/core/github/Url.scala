/*
 * Copyright 2018-2019 Scala Steward contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scalasteward.core.github

import org.http4s.Uri
import org.scalasteward.core.git.Branch
import org.scalasteward.core.vcs.data.Repo

class Url(apiHost: Uri) {
  def branches(repo: Repo, branch: Branch): Uri =
    repos(repo) / "branches" / branch.name

  def forks(repo: Repo): Uri =
    repos(repo) / "forks"

  def listPullRequests(repo: Repo, head: String, base: Branch): Uri =
    pulls(repo)
      .withQueryParam("head", head)
      .withQueryParam("base", base.name)
      .withQueryParam("state", "all")

  def pulls(repo: Repo): Uri =
    repos(repo) / "pulls"

  def repos(repo: Repo): Uri =
    apiHost / "repos" / repo.owner / repo.repo
}
