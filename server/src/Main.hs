{-# LANGUAGE BangPatterns      #-}
{-# LANGUAGE DeriveGeneric     #-}
{-# LANGUAGE OverloadedStrings #-}

module Main where

import           Control.Monad              (when)
import           Control.Monad.IO.Class     (liftIO)
import           Data.Aeson                 (FromJSON, ToJSON, decode, encode)
import           Data.Aeson.Encode.Pretty   (encodePretty)
import qualified Data.ByteString.Lazy.Char8 as B
import qualified Data.List                  as L
import           Data.Maybe                 (fromMaybe, maybe, isJust)
import           Data.Monoid                ((<>))
import qualified Data.Text.Lazy             as T
import qualified Data.Text.Lazy.Encoding    as T
import           GHC.Generics               (Generic)
import           Web.Scotty                 (ScottyM, body, delete, file, get,
                                             json, param, post, scotty, text)

type UniqueId = Integer

data TodoItem = TodoItem
  { name        :: T.Text
  , description :: T.Text
  , uniqueId    :: UniqueId
  } deriving (Show, Generic)

instance Eq TodoItem where
  t1 == t2 = uniqueId t1 == uniqueId t2

-- Switch from List to Set since ids should be unique

instance ToJSON TodoItem
instance FromJSON TodoItem

main :: IO ()
main = do
  putStrLn "Starting server..."
  scotty 3000 $ do
    getEndpoint
    postEndpoint
    deleteEndpoint
  where
    getEndpoint =
      get "/get" (file filename)

    postEndpoint =
      post "/post" $ do
        b <- body
        liftIO $ maybe (return ()) addItem (decode b)
        file filename

    deleteEndpoint =
      -- delete "/delete/:unique_id" $ do
      --   idToDelete <- param "unique_id"
      delete "/delete" $ do
        idToDelete <- param "id"
        liftIO $ deleteItem idToDelete

filename = "server_state.json"

readSaved :: IO (Maybe [TodoItem])
readSaved = decode <$> B.readFile filename

saveItems :: [TodoItem] -> IO ()

saveItems = B.writeFile filename . encodePretty

-- deleteItem :: TodoItem -> IO ()
-- deleteItem i = do
--   !maybeOld <- readSaved
--   saveItems (L.delete i (fromMaybe mempty maybeOld))
deleteItem :: UniqueId -> IO ()
deleteItem i = do
  !maybeOld <- readSaved
  let remainingItems = deleteId i <$> maybeOld
  maybe (return ()) saveItems remainingItems
   where
    deleteId i = L.filter ((/= i) . uniqueId)

addItem :: TodoItem -> IO ()
addItem i = do
  !maybeOld <- readSaved
  let newItems = (i:) <$> maybeOld
  maybe (return ()) saveItems newItems
-- addItem i = readSaved >>= saveItems . (i :) . fromMaybe mempty
